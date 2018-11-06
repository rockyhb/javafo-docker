#!/bin/sh

cat > trn.trfx
java -jar javafo.jar trn.trfx -p outfile.txt
cat outfile.txt
