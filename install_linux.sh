#!/bin/bash
echo "The Program will be installe in /vlej/1.0"
mkdir /vlej
mkdir /vlej/1.0
jar cvfm vlej.jar manifest.txt *.class
cp vlej.jar /vlej/1.0
cd /bin
cat > vlej.sh << EOF1
#!/bin/bash
java -jar /vlej/1.0/vlej.jar $1
EOF1