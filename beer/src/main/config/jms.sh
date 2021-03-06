# export GLASSFISH_HOME=/home/martijn/workspaces/cursussen/nljug-completablefuture/servers/glassfish4.1-release
export PATH=$GLASSFISH_HOME/bin:$PATH
# sh src/main/config/jms-resources.script
asadmin delete-jms-resource jms/ConnectionFactory
asadmin delete-jms-resource jms/RecipeQueue
asadmin delete-jms-resource jms/RecipeReplyQueue

asadmin create-jms-resource --restype javax.jms.ConnectionFactory  --property ClientId=MyID jms/ConnectionFactory
asadmin create-jms-resource --restype javax.jms.Queue --property Name=RecipeQueue jms/RecipeQueue
asadmin create-jms-resource --restype javax.jms.Queue --property Name=RecipeQueue jms/RecipeReplyQueue
