# Install Java Corretto 11
sudo yum install java-11-amazon-corretto-headless

# Set up Maven
wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar -xvf apache-maven-3.6.3-bin.tar.gz
sudo mv apache-maven-3.6.3 /opt/

M2_HOME='/opt/apache-maven-3.6.3'
PATH="$M2_HOME/bin:$PATH"
export PATH

# Install Git
sudo yum install git

# Verify Installations
java -version
git --version
mvn -version

# Clone the repo
git clone https://github.com/ABusyProgrammer/cca-498-mp2.git