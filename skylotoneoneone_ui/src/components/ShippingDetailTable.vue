
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Shipping Details</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalShippingDetails = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalShippingDetails">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add ShippingDetail</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="ShippingDetailId" type="text" placeholder="Enter ShippingDetailId" v-model="shippingDetailToAdd.shippingDetailId"></base-input>
  <base-input label="Address" type="text" placeholder="Enter Address" v-model="shippingDetailToAdd.address"></base-input>
  <base-input label="City" type="text" placeholder="Enter City" v-model="shippingDetailToAdd.city"></base-input>
  <base-input label="PostalCode" type="text" placeholder="Enter PostalCode" v-model="shippingDetailToAdd.postalCode"></base-input>
  <base-input label="Country" type="text" placeholder="Enter Country" v-model="shippingDetailToAdd.country"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="shippingDetails" :row-key="record => record.ShippingDetailId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <ShippingDetailPictureView :shippingDetails="shippingDetails" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import ShippingDetailService from "../services/ShippingDetailService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import ShippingDetailPictureView from './ShippingDetailPictureView.vue';


const shippingDetailsColumns = [
  "shippingDetailId",
  "year",
  "date",
  "competitionId",
  "shippingDetailId"
]

export default {
  props: {
    shippingDetails: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    ShippingDetailPictureView
  },

  data() {
    return {
      modalShippingDetails: false,
        isTableView: true,

      columns: [
        {
          title: 'Shipping Detail Id',
		dataIndex: 'shippingDetailId',
          visible: true,
          scopedSlots: { customRender: 'shippingDetailId' },
          sorter: true
          //sorter: (a, b) => a.shippingDetailId - b.shippingDetailId,
          //sorter: (a, b) => a.shippingDetailId.localeCompare(b.shippingDetailId),
        },
        {
          title: 'Address',
		dataIndex: 'address',
          visible: true,
          scopedSlots: { customRender: 'address' },
          sorter: true
          //sorter: (a, b) => a.address - b.address,
          //sorter: (a, b) => a.address.localeCompare(b.address),
        },
        {
          title: 'City',
		dataIndex: 'city',
          visible: true,
          scopedSlots: { customRender: 'city' },
          sorter: true
          //sorter: (a, b) => a.city - b.city,
          //sorter: (a, b) => a.city.localeCompare(b.city),
        },
        {
          title: 'Postal Code',
		dataIndex: 'postalCode',
          visible: true,
          scopedSlots: { customRender: 'postalCode' },
          sorter: true
          //sorter: (a, b) => a.postalCode - b.postalCode,
          //sorter: (a, b) => a.postalCode.localeCompare(b.postalCode),
        },
        {
          title: 'Country',
		dataIndex: 'country',
          visible: true,
          scopedSlots: { customRender: 'country' },
          sorter: true
          //sorter: (a, b) => a.country - b.country,
          //sorter: (a, b) => a.country.localeCompare(b.country),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} shippingDetails`,
      },

      shippingDetails: [],
      shippingDetailToAdd: {},

      shippingDetailsTable: {
        columns: [...shippingDetailsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'shippingDetailId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderShippingDetailsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let shippingDetailsTableData = [];
      for (let i = 0; i < this.shippingDetails.length; i++) {
        shippingDetailsTableData.push({
          id: i,
          shippingDetailId: this.shippingDetails[i].shippingDetailId,
          year: this.shippingDetails[i].year,
          date: this.shippingDetails[i].date,
          competitionId: this.shippingDetails[i].competitionId,
          shippingDetailId: this.shippingDetails[i].shippingDetailId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-shipping-details',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToUserDetail(id) {
      this.$router.push({ name: 'UserDetail', params: { userId: id.toString() }})
    },
    routingToItemDetail(id) {
      this.$router.push({ name: 'ItemDetail', params: { itemId: id.toString() }})
    },
    routingToBidDetail(id) {
      this.$router.push({ name: 'BidDetail', params: { bidId: id.toString() }})
    },
    routingToCategoryDetail(id) {
      this.$router.push({ name: 'CategoryDetail', params: { categoryId: id.toString() }})
    },
    routingToFeedbackDetail(id) {
      this.$router.push({ name: 'FeedbackDetail', params: { feedbackId: id.toString() }})
    },
    routingToTransactionDetail(id) {
      this.$router.push({ name: 'TransactionDetail', params: { transactionId: id.toString() }})
    },
    routingToPaymentMethodDetail(id) {
      this.$router.push({ name: 'PaymentMethodDetail', params: { paymentMethodId: id.toString() }})
    },
    routingToShippingDetailDetail(id) {
      this.$router.push({ name: 'ShippingDetailDetail', params: { shippingDetailId: id.toString() }})
    },
    routingToWatchlistDetail(id) {
      this.$router.push({ name: 'WatchlistDetail', params: { watchlistId: id.toString() }})
    },
    routingToMessageDetail(id) {
      this.$router.push({ name: 'MessageDetail', params: { messageId: id.toString() }})
    },
    routingToNotificationDetail(id) {
      this.$router.push({ name: 'NotificationDetail', params: { notificationId: id.toString() }})
    },
    routingToPromotionDetail(id) {
      this.$router.push({ name: 'PromotionDetail', params: { promotionId: id.toString() }})
    },
    routingToRoleDetail(id) {
      this.$router.push({ name: 'RoleDetail', params: { roleId: id.toString() }})
    },
    routingToPermissionDetail(id) {
      this.$router.push({ name: 'PermissionDetail', params: { permissionId: id.toString() }})
    },
    routingToSessionDetail(id) {
      this.$router.push({ name: 'SessionDetail', params: { sessionId: id.toString() }})
    },
    routingToReportDetail(id) {
      this.$router.push({ name: 'ReportDetail', params: { reportId: id.toString() }})
    },
    routingToFlagDetail(id) {
      this.$router.push({ name: 'FlagDetail', params: { flagId: id.toString() }})
    },
    routingToAlertDetail(id) {
      this.$router.push({ name: 'AlertDetail', params: { alertId: id.toString() }})
    },
    routingToSubscriptionDetail(id) {
      this.$router.push({ name: 'SubscriptionDetail', params: { subscriptionId: id.toString() }})
    },
    routingToTagDetail(id) {
      this.$router.push({ name: 'TagDetail', params: { tagId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForShippingDetailsChanged', this.searchQuery);
		//this.renderShippingDetailsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalShippingDetails = false;

      const currentDate = new Date().getTime();
      this.shippingDetailToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.shippingDetailToAdd);
      console.log(jsonData);
      
      const res = await ShippingDetailService.addShippingDetail(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderShippingDetailsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
