	@Override
	public ArrayList<Customer> getCustomerList(){
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = null;
        try {
            post = new HttpPost("https://my.fastbill.com/api/1.0/api.php");
            String encoding = DatatypeConverter.printBase64Binary((iprop.getUserMail() +":"+ iprop.getID()).getBytes("UTF-8"));
            
            post.setHeader("Authorization", "Basic " + encoding);
            String json = "{\"SERVICE\":\"customer.get\",\"FILTER\":{}}";
            HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
            post.setEntity(entity);
            
            HttpResponse response = httpClient.execute(post);
         
                String jsonString = EntityUtils.toString(response.getEntity());
                JSONObject root = new JSONObject(jsonString);
                JSONObject jsonResponse = (JSONObject) root.get("RESPONSE");
                JSONArray customers = (JSONArray) jsonResponse.get("CUSTOMERS");
                
                for(int i = 0 ; i < customers.length(); i++){
                	JSONObject customer = (JSONObject) customers.get(i);
                	Customer thisCustomer = new Customer(customer.getInt("CUSTOMER_NUMBER"), customer.getString("CUSTOMER_TYPE"),
                			customer.getString("ORGANIZATION"),customer.getString("LAST_NAME"));
                	customerList.add(thisCustomer);
                }

        } catch (Exception e) {
			e.printStackTrace();
		} finally {
            post.abort();
        }
        
        return customerList; 
	}