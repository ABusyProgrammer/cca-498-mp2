import requests
import json

url = 'https://seorwrpmwh.execute-api.us-east-1.amazonaws.com/prod/mp2-autograder-2022-spring'

payload = {
		'ip_address1': '3.133.137.187:8080',
		'ip_address2': '18.218.177.242:8080',
		'load_balancer' : 'MP2-546641303.us-east-2.elb.amazonaws.com',
		'submitterEmail': 'aryansk2@illinois.edu',
		'secret': 'O4RMq0Ugatd0NCw2'
		}


r = requests.post(url, data=json.dumps(payload))

print(r.status_code, r.reason)
print(r.text)
