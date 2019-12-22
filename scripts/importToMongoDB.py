from pymongo import MongoClient
import os
import json
import codecs
import csv
import time


URI = 'mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false'
SOURCE_DIRECTORY = '../data/details/'
PRICE_DIRECTORY = '../data/prices/price-history/'
SOURCE_FILES = os.listdir(SOURCE_DIRECTORY)

record = open('record.txt', 'r')
added = []
for line in record.read().split():
    added.append(line)
record.close()

record = open('record.txt', 'a')
client = MongoClient(URI)
db = client.steamdb
collection = db.apps

def combine_with_prices(json_dict, appid):
    price_list = []
    if 'price_overview' in json_dict:
        old_price = json_dict.pop('price_overview')
        try:
            with open(PRICE_DIRECTORY+'price-history-for-%s.csv' % (appid), 'r', encoding='utf-8-sig') as csvfile:
                pricereader = list(csv.reader(csvfile, delimiter=',', quotechar='"'))
                header = ['date', 'price']
                for i in range(1, len(pricereader)):
                    row = pricereader[i]
                    date = row[0]
                    price = row[1]
                    price_list.append({header[0]:date, header[1]:price})
            print('price list in %s' % (appid))
        except:
            print('single price in %s' % (appid))
            price_list.append({ 'date':time.strftime('%Y-%m-%d %H:%M:%S'), 'price':old_price['initial']//100})
    
    json_dict['prices'] = price_list

for filename in SOURCE_FILES:
    appid = (filename.split('-')[1]).split('.')[0]
    if appid in added:
        print('Skipping %s.' % (appid))
        continue
    filepath = SOURCE_DIRECTORY + filename
    with codecs.open(filepath, 'r', 'utf-8-sig') as fin:
        content = fin.read()
        json_dict = json.loads(content)
        for key in json_dict:
            json_dict = json_dict[key]
            break
    if (json_dict['success'] == True):
        json_dict = json_dict['data']
        combine_with_prices(json_dict, appid)
        collection.insert_one(json_dict)
    print('%s added to MongoDB.' % (appid))
    record.write(appid+'\n')
    
client.close()
record.close()
