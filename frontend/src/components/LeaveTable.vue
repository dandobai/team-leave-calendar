<script setup>
defineProps({
  leaveRequests: {
    type: Array,
    required: true
  }
})

defineEmits(['updateStatus'])
</script>

<template>
  <div class="bg-slate-800 border border-slate-700 p-6 rounded-lg shadow-xl lg:col-span-2 space-y-4">
    <h2 class="text-lg font-semibold text-white uppercase tracking-wide">Szabadság kérelmek</h2>
    
    <div class="overflow-x-auto rounded-lg border border-slate-700">
      <table class="w-full text-left border-collapse text-sm">
        <thead>
          <tr class="bg-slate-900 border-b border-slate-700 text-slate-300 font-medium uppercase text-xs tracking-wider">
            <th class="p-3">Név</th>
            <th class="p-3">Időszak</th>
            <th class="p-3">Indok</th>
            <th class="p-3">Státusz</th>
            <th class="p-3 text-right">Műveletek</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="req in leaveRequests" :key="req.id" class="border-b border-slate-700/50 hover:bg-slate-700/40 transition-colors">
            <td class="p-3 font-semibold text-white">{{ req.teamMember.name }}</td>
            <td class="p-3 text-slate-300 text-xs font-mono">{{ req.startDate }} ➔ {{ req.endDate }}</td>
            <td class="p-3 text-slate-400 italic">{{ req.reason }}</td>
            <td class="p-3">
              <span :class="{
                'bg-yellow-500/20 text-yellow-300 border border-yellow-500/30': req.status === 'PENDING',
                'bg-green-500/20 text-green-300 border border-green-500/30': req.status === 'APPROVED',
                'bg-red-500/20 text-red-300 border border-red-500/30': req.status === 'REJECTED'
              }" class="px-2 py-1 rounded text-xs font-bold tracking-wide uppercase">
                {{ req.status }}
              </span>
            </td>
            <td class="p-3 text-right space-x-1 whitespace-nowrap">
              <button 
                v-if="req.status === 'PENDING'" 
                @click="$emit('updateStatus', req.id, 'APPROVED')" 
                class="bg-green-600 hover:bg-green-700 text-white text-xs font-bold px-3 py-1 rounded transition shadow-md"
              >
                Approve
              </button>
              <button 
                v-if="req.status === 'PENDING'" 
                @click="$emit('updateStatus', req.id, 'REJECTED')" 
                class="bg-red-600 hover:bg-red-700 text-white text-xs font-bold px-3 py-1 rounded transition shadow-md"
              >
                Reject
              </button>
            </td>
          </tr>
          <tr v-if="leaveRequests.length === 0">
            <td colspan="5" class="p-6 text-center text-slate-500 italic">Nincs még benyújtott szabadságigény.</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
