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
            <flag-table
            v-if="flags && flags.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:flags="flags"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-flags="getAllFlags"
             >

            </flag-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FlagTable from "@/components/FlagTable";
import FlagService from "../services/FlagService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FlagTable,
  },
  data() {
    return {
      flags: [],
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
    async getAllFlags(sortBy='flagId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FlagService.getAllFlags(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.flags.length) {
					this.flags = response.data.flags;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching flags:", error);
        }
        
      } catch (error) {
        console.error("Error fetching flag details:", error);
      }
    },
  },
  mounted() {
    this.getAllFlags();
  },
  created() {
    this.$root.$on('searchQueryForFlagsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFlags();
    })
  }
};
</script>
<style></style>
