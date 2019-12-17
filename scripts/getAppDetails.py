import requests
import codecs
import json
from time import sleep
from os import path

proxies = {
"http": "http://127.0.0.1:1080",
"https": "http://127.0.0.1:1080",
}
region = 'CN'
language = 'schinese'
start = 16
end = 32
appid_list = []

for postfix in range(16, 32):
    fin = open('sorted_appids/sorted_appids-%i.txt' % (postfix))
    appid_list += fin.read().split()
    fin.close()

for appid in appid_list:
    filename = 'details/detail-%s.txt' % (appid)
    if path.exists(filename):
        continue
    # 200 requests every 5 minutes
    sleep(0.2)
    url = 'https://store.steampowered.com/api/appdetails?appids=%s&cc=%s&l=%s' % (appid, region, language)
    while True:
        try:
            response = requests.get(url, proxies=proxies)
            print('Getting APPID %s succeeded.' % (appid))
            break
        except:
            print('Retrying on APPID %s.' % (appid))
    try:
        json_dict = response.json()
        with codecs.open(filename, 'w', 'utf-8-sig') as fout:
            fout.write(json.dumps(json_dict, sort_keys=False, indent=4))
    except:
        print('Format Error')
    
