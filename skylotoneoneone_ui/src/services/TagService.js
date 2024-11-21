import http from "../http-common"; 

class TagService {
  getAllTags(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/tag/tags`, searchDTO);
  }

  get(tagId) {
    return this.getRequest(`/tag/${tagId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/tag?field=${matchData}`, null);
  }

  addTag(data) {
    return http.post("/tag/addTag", data);
  }

  update(data) {
  	return http.post("/tag/updateTag", data);
  }
  
  uploadImage(data,tagId) {
  	return http.postForm("/tag/uploadImage/"+tagId, data);
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

export default new TagService();
