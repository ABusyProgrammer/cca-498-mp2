import requests
import json

url = 'https://seorwrpmwh.execute-api.us-east-1.amazonaws.com/prod/mp2-autograder-2022-spring'

payload = {
		'ip_address1': '3.15.229.90:8080',
		'ip_address2': '3.15.31.78:8080',
		'load_balancer' : 'MP2-1899071774.us-east-2.elb.amazonaws.com',
		'submitterEmail': 'aryansk2@illinois.edu',
		'secret': '43e6zLpa3kbgolny'
		}


r = requests.post(url, data=json.dumps(payload))

print(r.status_code, r.reason)
print(r.text)
