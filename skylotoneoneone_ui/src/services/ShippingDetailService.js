import http from "../http-common"; 

class ShippingDetailService {
  getAllShippingDetails(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/shippingDetail/shippingDetails`, searchDTO);
  }

  get(shippingDetailId) {
    return this.getRequest(`/shippingDetail/${shippingDetailId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/shippingDetail?field=${matchData}`, null);
  }

  addShippingDetail(data) {
    return http.post("/shippingDetail/addShippingDetail", data);
  }

  update(data) {
  	return http.post("/shippingDetail/updateShippingDetail", data);
  }
  
  uploadImage(data,shippingDetailId) {
  	return http.postForm("/shippingDetail/uploadImage/"+shippingDetailId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new ShippingDetailService();
