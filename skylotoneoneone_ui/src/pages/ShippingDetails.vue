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
            <shippingDetail-table
            v-if="shippingDetails && shippingDetails.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:shippingDetails="shippingDetails"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-shipping-details="getAllShippingDetails"
             >

            </shippingDetail-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ShippingDetailTable from "@/components/ShippingDetailTable";
import ShippingDetailService from "../services/ShippingDetailService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ShippingDetailTable,
  },
  data() {
    return {
      shippingDetails: [],
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
    async getAllShippingDetails(sortBy='shippingDetailId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ShippingDetailService.getAllShippingDetails(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.shippingDetails.length) {
					this.shippingDetails = response.data.shippingDetails;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching shippingDetails:", error);
        }
        
      } catch (error) {
        console.error("Error fetching shippingDetail details:", error);
      }
    },
  },
  mounted() {
    this.getAllShippingDetails();
  },
  created() {
    this.$root.$on('searchQueryForShippingDetailsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllShippingDetails();
    })
  }
};
</script>
<style></style>
