#!/bin/bash

target=/tmp/inotes-install
srcdir=~/bin
destdir=$(pwd)

rm -rf $target
mkdir $target

cd ~/bin
cp $srcdir/iNotes-exporter-1.8-jar-with-dependencies.jar $srcdir/webmailtrust $target
sed 's/keyStorePassword=.*/keyStorePassword=####### \\/' $srcdir/start-inotes-cert.sh > $target/start-inotes-cert.sh
chmod +x $target/start-inotes-cert.sh
touch $target/webmailclientcert.p12

cd $(dirname $target)
tar -cvzf $destdir/inotes-install.tgz -C $(dirname $target) $(basename $target)
