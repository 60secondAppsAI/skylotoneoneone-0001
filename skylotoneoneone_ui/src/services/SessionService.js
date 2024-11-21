import http from "../http-common"; 

class SessionService {
  getAllSessions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/session/sessions`, searchDTO);
  }

  get(sessionId) {
    return this.getRequest(`/session/${sessionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/session?field=${matchData}`, null);
  }

  addSession(data) {
    return http.post("/session/addSession", data);
  }

  update(data) {
  	return http.post("/session/updateSession", data);
  }
  
  uploadImage(data,sessionId) {
  	return http.postForm("/session/uploadImage/"+sessionId, data);
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

export default new SessionService();
