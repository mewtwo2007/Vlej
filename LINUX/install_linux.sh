#!/bin/bash
echo "The Program will be installed in /vlej/1.0"
mkdir /vlej
mkdir /vlej/1.0
cd Source
jar cvfm vlej.jar manifest.txt *.class
cp vlej.jar /vlej/1.0
chmod +x /vlej/1.0/vlej.jar
cd /bin
cat > vlej << EOF1
#!/bin/bash
java -jar /vlej/1.0/vlej.jar $1
EOF1