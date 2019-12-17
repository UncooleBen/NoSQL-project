from pymongo import MongoClient
import os
import json
import codecs

URI = 'mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false'
SOURCE_DIRECTORY = '../details/'
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

for filename in SOURCE_FILES:
    appid = (filename.split('-')[1]).split('.')[0]
    if appid in added:
        print('Skipping %s.' % (appid))
        continue
    filepath = SOURCE_DIRECTORY + filename
    with codecs.open(filepath, 'r', 'utf-8-sig') as fin:
        content = fin.read()
        json_dict = json.loads(content)
    collection.insert_one(json_dict)
    print('%s added to MongoDB.' % (appid))
    record.write(appid+'\n')
    
client.close()
record.close()
