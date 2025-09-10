# envoy_demo

I want to create a program which demonstrates the capabilities of the Envoy sidecar.

Create an openshift pod deployment for a spring boot microservice.
this pod should have as a sidecar an Envoy container.
The envoy container will proxy all requests to my spring boot microservice.

the envoy container however, will only proxy the requests to my spring boot application if
the request to the proxy contains a valid bearer token in the Authorization header.
This bearer token must be issued by entraID and must be issued for a fixed service identity to be valid.
if the request doesn't contain a valid authorization header with a valid bearer token envoy should reply with
an authorization http error. if all is valid then it should proxy the request to my spring boot service.

my spring boot service has one GET endpoint called /hello-world and envoy will proxy requests to it.

to be clear the envoy proxy and the spring boot app are deployed together in the same openshift pod.

for now assume that envoy is configured completely from variables provided to the Deployment configuration.


i also want to configure envoy to work as an outgoing proxy.
any requests my spring boot app makes to downstream services will first go to envoy.
envoy will then determine which service the request is going out to based on the path in the request.
it will then determine which identity provided it needs to invoke to get an access token for the request using the authorization code flow
the id and secret for the identity are provided as variables to the envoy configuration.

there may be more than one identity it uses to invoke downstream services, so this should be selectable based on the downstream service it's calling.

envoy will inject the access token in the the Authorization header so that i dont have to modify my application code to call these downstream services.

Envoy use should be designed to ensure that my spring boot application logic remains completely unchanged, yet it is able to authorize all requests to the service and also inject access tokens to the downstream services it calls.

this is all intended to be minimally invasive so that i don't need to make any changes to my spring boot application code.