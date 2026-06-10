<script setup>
import { useLeaveManagement } from './composables/useLeaveManagement'

import AppHeader from './components/AppHeader.vue'
import NotificationAlert from './components/NotificationAlert.vue'
import LeaveForm from './components/LeaveForm.vue'
import LeaveTable from './components/LeaveTable.vue'
import OnCallSchedule from './components/OnCallSchedule.vue'

const {
  teamMembers,
  filteredLeaveRequests,
  filteredOnCallSchedule,
  selectedMemberId,
  errorMessage,
  successMessage,
  form,
  submitLeaveRequest,
  updateLeaveStatus
} = useLeaveManagement()
</script>

<template>
  <div class="min-h-screen bg-slate-900 text-white p-6 font-sans antialiased">
    <div class="max-w-7xl mx-auto space-y-6">
      
      <AppHeader v-model="selectedMemberId" :teamMembers="teamMembers" />

      <NotificationAlert :message="errorMessage" type="error" />
      <NotificationAlert :message="successMessage" type="success" />

      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <LeaveForm v-model="form" :teamMembers="teamMembers" @submit="submitLeaveRequest" />
        
        <LeaveTable :leaveRequests="filteredLeaveRequests" @updateStatus="updateLeaveStatus" />
      </div>

      <OnCallSchedule :onCallSchedule="filteredOnCallSchedule" />

    </div>
  </div>
</template>
