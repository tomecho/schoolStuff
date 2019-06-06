PROGS = client
OPTS = -O2 -Wall

all: $(PROGS)

client: client.c csapp.o csapp.h
	gcc $(OPTS) client.c csapp.o -pthread -o client

csapp.o: csapp.c csapp.h
	gcc $(OPTS) -c csapp.c

clean:
	rm -f $(PROGS) *.o
