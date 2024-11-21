<template>
  <a-layout style="min-height: 100vh">
    <!-- Header -->
    <a-layout-header style="background: #fff; padding: 0">
      <app-header />
    </a-layout-header>

    <!-- Main layout with sider and content -->
    <a-layout>
      <!-- Collapsible Sider with Controlled Open Keys for Nested Menu -->
      <a-layout-sider width="200px" collapsible style="background: #fff">
        <a-menu
          mode="inline"
          :openKeys="openKeys"
          @openChange="onOpenChange"
          style="height: 100%; border-right: 0"
        >
<!--          <a-sub-menu key="sub1" title="Menu 1">
            <a-menu-item key="1">Option 1</a-menu-item>
            <a-menu-item key="2">Option 2</a-menu-item>
            <a-sub-menu key="sub1-1" title="Nested Menu">
              <a-menu-item key="3">Option 3</a-menu-item>
              <a-menu-item key="4">Option 4</a-menu-item>
            </a-sub-menu>
          </a-sub-menu>
          <a-sub-menu key="sub2" title="Menu 2">
            <a-menu-item key="5">Option 5</a-menu-item>
            <a-menu-item key="6">Option 6</a-menu-item>
          </a-sub-menu>
-->>

	 
			
				<a-menu-item>
					<router-link to="/users">
						<span class="label">Users</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/items">
						<span class="label">Items</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/bids">
						<span class="label">Bids</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/categorys">
						<span class="label">Categorys</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/feedbacks">
						<span class="label">Feedbacks</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/transactions">
						<span class="label">Transactions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/paymentMethods">
						<span class="label">Payment Methods</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/shippingDetails">
						<span class="label">Shipping Details</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/watchlists">
						<span class="label">Watchlists</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/messages">
						<span class="label">Messages</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/notifications">
						<span class="label">Notifications</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/promotions">
						<span class="label">Promotions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/roles">
						<span class="label">Roles</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/permissions">
						<span class="label">Permissions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/sessions">
						<span class="label">Sessions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/reports">
						<span class="label">Reports</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/flags">
						<span class="label">Flags</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/alerts">
						<span class="label">Alerts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/subscriptions">
						<span class="label">Subscriptions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/tags">
						<span class="label">Tags</span>
					</router-link>
				</a-menu-item>
		
	
        </a-menu>
      </a-layout-sider>
      
      <!-- Content area -->
      <a-layout-content style="padding: 16px">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { ref } from "vue";
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "DefaultLayout",
  components: {
    AppHeader,
  },
  setup() {
    const openKeys = ref(["sub1"]); // default open submenu
    const onOpenChange = (keys) => {
      // Open only the last clicked submenu, for single open behavior
      openKeys.value = keys.length ? [keys[keys.length - 1]] : [];
    };

    return {
      openKeys,
      onOpenChange,
    };
  },
};
</script>
<style>
.ant-layout {
    margin-top: 20px;
}
</style>

