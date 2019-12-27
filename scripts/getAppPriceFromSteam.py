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

def getAppPriceAndResponse(appid):
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
    return price,response

def getAppDetail(appid,response):
    jsonData=response.json()['%s'%appid]['data']
    newApp={}
    #string
    newApp['type']=jsonData['type']
    #string
    newApp['name']=jsonData['name']
    #int
    newApp['steam_appid']=jsonData['steam_appid']
    #int
    newApp['required_age']=jsonData['required_age']
    #boolean
    newApp['is_free']=jsonData['is_free']
    #string
    newApp['detailed_description']=jsonData['detailed_description']
    #string
    newApp['about_the_game']=jsonData['about_the_game']
    #string
    newApp['short_description']=jsonData['short_description']
    #string
    newApp['supported_languages']=jsonData['supported_languages']
    #string
    newApp['header_image']=jsonData['header_image']
    #string
    newApp['website']=jsonData['website']
    #object
    newApp['pc_requirements']=jsonData['pc_requirements']
    #object
    newApp['mac_requirements']=jsonData['mac_requirements']
    #object
    newApp['linux_requirements']=jsonData['linux_requirements']
    #array
    newApp['developers']=jsonData['developers']
    #array
    newApp['publishers']=jsonData['publishers']
    #array
    newApp['packages']=jsonData['packages']
    #array
    newApp['package_groups']=jsonData['package_groups']
    #object
    newApp['platforms']=jsonData['platforms']
    #object
    newApp['metacritic']=jsonData['metacritic']
    #array
    newApp['categories']=jsonData['categories']
    #array
    newApp['genres']=jsonData['genres']
    #array
    newApp['screenshots']=jsonData['screenshots']
    #object
    newApp['recommendations']=jsonData['recommendations']
    #object
    newApp['achievements']=jsonData['achievements']
    #object
    newApp['release_date']=jsonData['release_date']
    #object
    newApp['support_info']=jsonData['support_info']
    #string
    newApp['background']=jsonData['background']
    #object
    newApp['content_descriptors']=jsonData['content_descriptors']
    return newApp

def updateAppidInDatabase(appid,logFile):
    client,collection=getConnection()
    logFile.write('working on %d\n'%appid)
    price,response=getAppPriceAndResponse(appid)
    if -2==price or -3==price:
        return
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
        collection.update_many(queryFilter,newValue)
        logFile.write('updated %d\n'%appid)
    #if appid is newly added
    if not found:
        newApp=getAppDetail(appid,response)
        if False==newApp['is_free']:
            newApp['prices']=[{'date':currentDate,'price':price}]
        else:
            newApp['prices']=[]
        newApp['last_modified']=currentDate
        collection.insert_one(newApp)
        logFile.write('added %d\n'%appid)
    client.close()

if __name__ == "__main__":
    sys.stdout.reconfigure(encoding='utf-8-sig')
    #type(currentDate): string
    previousDate='1970-01-01'
    if not os.path.exists('.\\log'):
        os.mkdir('.\\log')
    while True:
        currentDate=(datetime.datetime.utcnow()+datetime.timedelta(hours=-8)).date().strftime('%Y-%m-%d')
        if previousDate<currentDate:
            errorReport=open('log\\error_report.txt','a')
            errorReport.write('------%s start------\n'%currentDate)
            try:
                logFile=open('log\\log_%s.txt'%currentDate,'w')
                appidList=[]
                getSortedAppids(appidList)
                for appid in appidList:
                    try:
                        updateAppidInDatabase(appid,logFile)
                    except:
                        errorReport.write('exception occurs when working on appid %s\n'%appid)
                previousDate=currentDate
                logFile.close()
            except:
                errorReport.write('unknown exception occurs\n')
            errorReport.write('------%s end------\n'%currentDate)
            errorReport.close()
        else:
        #check time once an hour
            time.sleep(3600)
    
    
    

    
        
    
