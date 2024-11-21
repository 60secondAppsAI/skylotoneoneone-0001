<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <session-table
            v-if="sessions && sessions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:sessions="sessions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-sessions="getAllSessions"
             >

            </session-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SessionTable from "@/components/SessionTable";
import SessionService from "../services/SessionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SessionTable,
  },
  data() {
    return {
      sessions: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllSessions(sortBy='sessionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SessionService.getAllSessions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.sessions.length) {
					this.sessions = response.data.sessions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching sessions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching session details:", error);
      }
    },
  },
  mounted() {
    this.getAllSessions();
  },
  created() {
    this.$root.$on('searchQueryForSessionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSessions();
    })
  }
};
</script>
<style></style>
