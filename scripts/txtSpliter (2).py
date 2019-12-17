if __name__=='__main__':
    fin=open('appids0.txt')
    l=fin.read().split()
    fin=open('appids1.txt')
    l = l + fin.read().split()
    fin=open('appids2.txt')
    l = l + fin.read().split()
    fin.close()
    for i in range(len(l)):
        l[i] = int(l[i])
    l.sort()
    length=len(l)
    block=length//48
    for i in range(0,48):
        fout=open('sorted_appids-'+str(i)+'.txt','w')
        print('from %d to %d\n'%(i*block,(i+1)*block))
        for appid in range(i*block,(i+1)*block):
            fout.write(str(l[appid])+'\n')
        fout.close()