import socket
import time
import sys

while True:
    print('stop or x,y,oper: '),
    q = sys.stdin.readline()
    d= 0.1
    if 'stop' in q:
        raise Exception # we are done
    print('requesting ' + q)
    while True:
        s = socket.socket(socket.AF_INET,socket.SOCK_DGRAM) # socket for recieving
        s2 = socket.socket(socket.AF_INET,socket.SOCK_DGRAM) # socket for sending

        s2.sendto(q.encode(),("127.0.0.1", 12345))
        s.bind(("127.0.0.1", 12344))
        s.settimeout(d)
        try:
            data,addr = s.recvfrom(1024)
            if str(data).startswith('300'):
                print('hey there was an error with your request')
            print(str(data) + 'recieved from ' + str(addr))
            break; # dont send the same thing, go up one level and send something else
        except socket.timeout as e:
            d=d*2
            if d>2:
                raise Exception # stop here
        finally:
            s.close() # always close the socket!
            s2.close()
