import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Items from  '@/pages/Items.vue';
import ItemDetail from  '@/pages/ItemDetail.vue';
import Bids from  '@/pages/Bids.vue';
import BidDetail from  '@/pages/BidDetail.vue';
import Categorys from  '@/pages/Categorys.vue';
import CategoryDetail from  '@/pages/CategoryDetail.vue';
import Feedbacks from  '@/pages/Feedbacks.vue';
import FeedbackDetail from  '@/pages/FeedbackDetail.vue';
import Transactions from  '@/pages/Transactions.vue';
import TransactionDetail from  '@/pages/TransactionDetail.vue';
import PaymentMethods from  '@/pages/PaymentMethods.vue';
import PaymentMethodDetail from  '@/pages/PaymentMethodDetail.vue';
import ShippingDetails from  '@/pages/ShippingDetails.vue';
import ShippingDetailDetail from  '@/pages/ShippingDetailDetail.vue';
import Watchlists from  '@/pages/Watchlists.vue';
import WatchlistDetail from  '@/pages/WatchlistDetail.vue';
import Messages from  '@/pages/Messages.vue';
import MessageDetail from  '@/pages/MessageDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Promotions from  '@/pages/Promotions.vue';
import PromotionDetail from  '@/pages/PromotionDetail.vue';
import Roles from  '@/pages/Roles.vue';
import RoleDetail from  '@/pages/RoleDetail.vue';
import Permissions from  '@/pages/Permissions.vue';
import PermissionDetail from  '@/pages/PermissionDetail.vue';
import Sessions from  '@/pages/Sessions.vue';
import SessionDetail from  '@/pages/SessionDetail.vue';
import Reports from  '@/pages/Reports.vue';
import ReportDetail from  '@/pages/ReportDetail.vue';
import Flags from  '@/pages/Flags.vue';
import FlagDetail from  '@/pages/FlagDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';
import Subscriptions from  '@/pages/Subscriptions.vue';
import SubscriptionDetail from  '@/pages/SubscriptionDetail.vue';
import Tags from  '@/pages/Tags.vue';
import TagDetail from  '@/pages/TagDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/users',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/items',
		name: 'Items',
		layout: DefaultLayout,
		component: Items,
	},
	{
	    path: '/item/:itemId', 
	    name: 'ItemDetail',
		layout: DefaultLayout,
	    component: ItemDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bids',
		name: 'Bids',
		layout: DefaultLayout,
		component: Bids,
	},
	{
	    path: '/bid/:bidId', 
	    name: 'BidDetail',
		layout: DefaultLayout,
	    component: BidDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/categorys',
		name: 'Categorys',
		layout: DefaultLayout,
		component: Categorys,
	},
	{
	    path: '/category/:categoryId', 
	    name: 'CategoryDetail',
		layout: DefaultLayout,
	    component: CategoryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/feedbacks',
		name: 'Feedbacks',
		layout: DefaultLayout,
		component: Feedbacks,
	},
	{
	    path: '/feedback/:feedbackId', 
	    name: 'FeedbackDetail',
		layout: DefaultLayout,
	    component: FeedbackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/transactions',
		name: 'Transactions',
		layout: DefaultLayout,
		component: Transactions,
	},
	{
	    path: '/transaction/:transactionId', 
	    name: 'TransactionDetail',
		layout: DefaultLayout,
	    component: TransactionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/paymentMethods',
		name: 'PaymentMethods',
		layout: DefaultLayout,
		component: PaymentMethods,
	},
	{
	    path: '/paymentMethod/:paymentMethodId', 
	    name: 'PaymentMethodDetail',
		layout: DefaultLayout,
	    component: PaymentMethodDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/shippingDetails',
		name: 'ShippingDetails',
		layout: DefaultLayout,
		component: ShippingDetails,
	},
	{
	    path: '/shippingDetail/:shippingDetailId', 
	    name: 'ShippingDetailDetail',
		layout: DefaultLayout,
	    component: ShippingDetailDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/watchlists',
		name: 'Watchlists',
		layout: DefaultLayout,
		component: Watchlists,
	},
	{
	    path: '/watchlist/:watchlistId', 
	    name: 'WatchlistDetail',
		layout: DefaultLayout,
	    component: WatchlistDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/messages',
		name: 'Messages',
		layout: DefaultLayout,
		component: Messages,
	},
	{
	    path: '/message/:messageId', 
	    name: 'MessageDetail',
		layout: DefaultLayout,
	    component: MessageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotions',
		name: 'Promotions',
		layout: DefaultLayout,
		component: Promotions,
	},
	{
	    path: '/promotion/:promotionId', 
	    name: 'PromotionDetail',
		layout: DefaultLayout,
	    component: PromotionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/roles',
		name: 'Roles',
		layout: DefaultLayout,
		component: Roles,
	},
	{
	    path: '/role/:roleId', 
	    name: 'RoleDetail',
		layout: DefaultLayout,
	    component: RoleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/permissions',
		name: 'Permissions',
		layout: DefaultLayout,
		component: Permissions,
	},
	{
	    path: '/permission/:permissionId', 
	    name: 'PermissionDetail',
		layout: DefaultLayout,
	    component: PermissionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/sessions',
		name: 'Sessions',
		layout: DefaultLayout,
		component: Sessions,
	},
	{
	    path: '/session/:sessionId', 
	    name: 'SessionDetail',
		layout: DefaultLayout,
	    component: SessionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reports',
		name: 'Reports',
		layout: DefaultLayout,
		component: Reports,
	},
	{
	    path: '/report/:reportId', 
	    name: 'ReportDetail',
		layout: DefaultLayout,
	    component: ReportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flags',
		name: 'Flags',
		layout: DefaultLayout,
		component: Flags,
	},
	{
	    path: '/flag/:flagId', 
	    name: 'FlagDetail',
		layout: DefaultLayout,
	    component: FlagDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: DefaultLayout,
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: DefaultLayout,
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/subscriptions',
		name: 'Subscriptions',
		layout: DefaultLayout,
		component: Subscriptions,
	},
	{
	    path: '/subscription/:subscriptionId', 
	    name: 'SubscriptionDetail',
		layout: DefaultLayout,
	    component: SubscriptionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tags',
		name: 'Tags',
		layout: DefaultLayout,
		component: Tags,
	},
	{
	    path: '/tag/:tagId', 
	    name: 'TagDetail',
		layout: DefaultLayout,
	    component: TagDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
