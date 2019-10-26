if __name__=='__main__':
	fin=open('appids0.txt')
	l=fin.read().split()
	size=len(l)
	block=size//16
	for i in range(0,16):
		fout=open('appids0-'+str(i)+'.txt','w')
		print('from %d to %d\n'%(i*block,(i+1)*block))
		for appid in range(i*block,(i+1)*block):
			fout.write(l[appid]+'\n')
		fout.close()
	fin.close()