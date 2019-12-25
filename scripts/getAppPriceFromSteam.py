from pymongo import MongoClient
import os
import json
import codecs
import csv
import time
import requests
import sys
import io
import pytz
import datetime

proxies = {
"http": "http://127.0.0.1:10809",
"https": "http://127.0.0.1:10809",
}

def getConnection():
    URI = 'mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false'
    client = MongoClient(URI)
    db = client['steamdb']
    collection = db['apps']
    return client,collection

def getSortedAppids(appidList):
    URL='https://api.steampowered.com/ISteamApps/GetAppList/v2/?'
    response=requests.get(URL,proxies=proxies)
    jsonDict=response.json()
    jsonList=jsonDict['applist']['apps']
    for app_dict in jsonList:
        #type(appidList[i]): int
        appidList.append(app_dict['appid'])
    appidList.sort()

def getAppPrice(appid):
    region='CN'
    language='schinese'
    #price: -3 -> unknown error, -2 -> success=false, -1 -> is_free=true , 0 -> discount_percent=100
    price=-2
    URL='https://store.steampowered.com/api/appdetails?appids=%s&cc=%s&l=%s' % (appid, region, language)
    response=requests.get(URL,proxies=proxies)
    jsonDict=response.json()['%s'%appid]
    if True==jsonDict['success']:
        if False==jsonDict['data']['is_free']:
            if 'price_overview' in jsonDict['data']:
                #type(price): int
                #price is stored as cents, should be converted to yuan
                price=jsonDict['data']['price_overview']['final']//100
            else:
                price=-3
        else:
            price=-1
    return price


# def combine_with_prices(json_dict, appid):
#     price_list = []
#     if 'price_overview' in json_dict:
#         old_price = json_dict.pop('price_overview')
#         try:
#             with open(PRICE_DIRECTORY+'price-history-for-%s.csv' % (appid), 'r', encoding='utf-8-sig') as csvfile:
#                 pricereader = list(csv.reader(csvfile, delimiter=',', quotechar='"'))
#                 header = ['date', 'price']
#                 for i in range(1, len(pricereader)):
#                     row = pricereader[i]
#                     date = row[0]
#                     price = row[1]
#                     price_list.append({header[0]:date, header[1]:price})
#             print('price list in %s' % (appid))
#         except:
#             print('single price in %s' % (appid))
#             price_list.append({ 'date':time.strftime('%Y-%m-%d %H:%M:%S'), 'price':old_price['initial']//100})
    
#     json_dict['prices'] = price_list

# for filename in SOURCE_FILES:
#     appid = (filename.split('-')[1]).split('.')[0]
#     if appid in added:
#         print('Skipping %s.' % (appid))
#         continue
#     filepath = SOURCE_DIRECTORY + filename
#     with codecs.open(filepath, 'r', 'utf-8-sig') as fin:
#         content = fin.read()
#         json_dict = json.loads(content)
#         for key in json_dict:
#             json_dict = json_dict[key]
#             break
#     if (json_dict['success'] == True):
#         json_dict = json_dict['data']
#         combine_with_prices(json_dict, appid)
#         collection.insert_one(json_dict)
#     print('%s added to MongoDB.' % (appid))
#     record.write(appid+'\n')
    
# client.close()
# record.close()

if __name__ == "__main__":
    sys.stdout.reconfigure(encoding='utf-8-sig')
    #type(currentDate): string
    currentDate=(datetime.datetime.utcnow()+datetime.timedelta(hours=-8)).date().strftime('%Y-%m-%d')
    client,collection=getConnection()
    appidList=[]
    getSortedAppids(appidList)
    for appid in appidList[:20]:
        price=getAppPrice(appid)
        if -2==price or -3==price:
            continue
        queryFilter={'steam_appid':appid}
        resultCursor=collection.find(queryFilter)
        newValue={}
        found=False
        #Should have only 1 result if exists
        for doc in resultCursor:
            found=True
            #Update price
            if 'is_free' in doc:
                if False==doc['is_free']:
                    if doc['prices'][-1][1]!=price:
                        if '$push' not in newValue:
                            newValue['$push']={}
                        newValue['$push']['prices']=[currentDate,price]
            #Update last_modified
            if 'last_modified' in doc:
                lastModified=doc['last_modified']
                if lastModified<currentDate:
                    if '$set' not in newValue:
                        newValue['$set']={}
                    newValue['$set']['last_modified']=currentDate
            else:
                if '$set' not in newValue:
                        newValue['$set']={}
                newValue['$set']['last_modified']=currentDate
        if found and len(newValue)!=0:
            updateCount=collection.update_many(queryFilter,newValue)
            print('updated %d'%appid)
    client.close()

    
        
    
