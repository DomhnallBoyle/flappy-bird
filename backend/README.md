### Quickstart:
```
export CONFIG=production
pip install -r requirements.txt
python server.py
```

### Tunnel forwarding:
```
# install localtunnel or other tunnel forwarding tools
sudo npm install -g localtunnel

# should give url as: http://flappy-bird.localtunnel.me
lt --port 8080 -h http://localtunnel.me -s flappy-bird
```