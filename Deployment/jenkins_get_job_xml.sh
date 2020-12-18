if [[ ! -e jenkins-cli.jar ]]
then
  echo ""
  echo "Download the jenkins CLI tool first."
  echo ""
  exit 1
fi

./jenkins_cli.sh get-job CodeSpyGlass
