SHELL := /bin/bash

start:
	bash -i -c "new_tab 'make client'"
	bash -i -c "new_tab 'git status'"
	clear; make setup && make server

#TODO: if prod...
server:
	lein repl

setup: datomic

datomic:
	lein datomic start &
	lein datomic initialize

#TODO: if prod...
client:
	rlwrap lein figwheel

test:
	lein test

clean:
	lein clean

help:
	@ echo "[HELP]: Run 'make', or run 'make setup && make server' & 'make client'"
	@ make -rpn | sed -n -e '/^$$/ { n ; /^[^ ]*:/p; }' | sort | tail -n +2 | egrep --color '^[^ ]*:'

.PHONY: start server client test clean help
