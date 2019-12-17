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
    fout = open('sorted_appid.txt', 'w')
    for id in l:
        fout.write(str(id) + '\n')
    fout.close()