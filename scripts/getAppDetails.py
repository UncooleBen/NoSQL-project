import requests
import codecs
import json
from time import sleep
from os import path

proxies = {
 "http": "http://127.0.0.1:10809",
 "https": "http://127.0.0.1:10809",
}
region = 'CN'
language = 'schinese'
start = 0
end = 16
appid_list = []

for postfix in range(0, 16):
    fin = open('sorted_appids/sorted_appids-%i.txt' % (postfix))
    appid_list += fin.read().split()
    fin.close()

fexception=open("exception.txt",'w')

for appid in appid_list:
    try:
        filename = 'details/detail-%s.txt' % (appid)
        if path.exists(filename):
            print("skip: "+filename)
            continue
        # 200 requests every 5 minutes
        sleep(1.5)
        url = 'https://store.steampowered.com/api/appdetails?appids=%s&cc=%s&l=%s' % (appid, region, language)
        with requests.get(url, proxies=proxies) as response:
            json_dict = response.json()
        print("output: "+filename)
        with codecs.open(filename, 'w', 'utf-8-sig') as fout:
            fout.write(json.dumps(json_dict, sort_keys=False, indent=4))
    except:
        fexception.write(str(appid)+"\n")
        print("exception occurs: "+filename)

fexception.close()
        

