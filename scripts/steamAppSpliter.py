import json

json_file = 'index.json'

json_data = open(json_file, encoding='utf-8')
data = json.load(json_data, encoding='utf-8')

size = len(data['applist']['apps']['app'])
print('size=%d' %(size))
applist = data['applist']['apps']['app']
for i in range(3):
    output_filename = 'appids'+str(i)+'.txt'
    output_file = open(output_filename, 'w')
    print('from %d, to %d' % (i*size//3, (i+1)*size//3 if i!=2 else size))
    for index in range(i*size//3, (i+1)*size//3 if i!=2 else size):
        output_file.write(str(applist[index]['appid'])+'\n')
