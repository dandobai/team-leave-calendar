<script setup>
defineProps({
  onCallSchedule: {
    type: Array,
    required: true
  }
})
</script>

<template>
  <section class="bg-slate-800 border border-slate-700 p-6 rounded-lg shadow-xl text-white">
    <h2 class="text-lg font-semibold text-white mb-4 uppercase tracking-wide">Weekly On-Call Rotation Schedule</h2>
    
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
      <div v-for="week in onCallSchedule" :key="week.weekNumber" 
           :class="{
             'border-red-500/50 bg-red-950/40 text-red-200 shadow-lg shadow-red-950/20': week.hasConflict,
             'border-slate-700 bg-slate-900 text-slate-100': !week.hasConflict
           }" 
           class="border rounded-lg p-4 flex flex-col justify-between transition-all duration-200 hover:scale-[1.02] hover:shadow-md">
        <div>
          <div class="flex justify-between items-center mb-2">
            <span class="text-xs font-bold uppercase tracking-wider text-slate-400">Week {{ week.weekNumber }}</span>
            <span class="text-xs text-slate-500">{{ week.year }}</span>
          </div>
          <p class="text-xs text-slate-400 font-mono mb-3">{{ week.weekStart }} – {{ week.weekEnd }}</p>
          
          <div class="flex items-center space-x-2 mt-2">
            <div class="h-2 w-2 rounded-full flex-shrink-0" :class="week.hasConflict ? 'bg-red-500 animate-pulse' : 'bg-green-500'"></div>
            <p class="font-bold text-sm tracking-wide" :class="week.hasConflict ? 'text-white' : 'text-slate-200'">
              {{ week.onCallMember.name }}
            </p>
          </div>
        </div>
        
        <div v-if="week.hasConflict" class="mt-4 pt-2 border-t border-red-500/30 flex items-center space-x-1.5 text-xs font-bold text-red-400 uppercase tracking-wide">
          <span>⚠️ Conflict: On leave!</span>
        </div>
      </div>
    </div>
  </section>
</template>
