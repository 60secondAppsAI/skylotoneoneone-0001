import http from "../http-common"; 

class FlagService {
  getAllFlags(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/flag/flags`, searchDTO);
  }

  get(flagId) {
    return this.getRequest(`/flag/${flagId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/flag?field=${matchData}`, null);
  }

  addFlag(data) {
    return http.post("/flag/addFlag", data);
  }

  update(data) {
  	return http.post("/flag/updateFlag", data);
  }
  
  uploadImage(data,flagId) {
  	return http.postForm("/flag/uploadImage/"+flagId, data);
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

export default new FlagService();
