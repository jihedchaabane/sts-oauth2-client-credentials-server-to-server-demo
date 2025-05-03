q# z-springboot-hello-world-service
-----------------------------------------------------
IN "10.0.0.137" do:
-----------------------------------------------------
sudo firewall-cmd --add-port=7774/tcp --permanent
sudo firewall-cmd --reload

sudo firewall-cmd --list-ports
sudo firewall-cmd --list-all

---Fermer le port 7774 -------------------------------
sudo firewall-cmd --permanent --remove-port=7774/tcp
sudo firewall-cmd --reload

sudo firewall-cmd --list-ports
sudo firewall-cmd --list-all
-----------------------------------------------------
http://10.0.0.137:7774/swagger-ui/index.html

curl -X 'GET' 'http://10.0.0.137:7774/products-view' -H 'accept: */*'
curl -X 'GET' 'http://10.0.0.137:7774/public' -H 'accept: */*'

-----------------------------------------------------