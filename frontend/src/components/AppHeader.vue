<script setup>
import exciteLogo from '../assets/excite-logo.png'

defineProps({
  teamMembers: {
    type: Array,
    required: true
  }
})

const model = defineModel({ type: String })
</script>

<template>
  <header class="bg-slate-800 border border-slate-700 p-6 rounded-lg shadow-xl flex flex-col md:flex-row md:justify-between md:items-center gap-4">
    
    <div class="flex items-center space-x-4 w-full md:w-auto">
      <img :src="exciteLogo" alt="Excite Hungary" class="h-10 md:h-12 w-auto object-contain flex-shrink-0" />
      <div class="border-l border-slate-600 pl-4">
        <h1 class="text-xl md:text-2xl font-bold text-white tracking-tight leading-none mb-1">INTERNAL PORTAL</h1>
        <p class="text-xs md:text-sm text-slate-400">On-Call Management System</p>
      </div>
    </div>
    
    <div class="flex flex-col sm:flex-row sm:items-center gap-2 w-full md:w-auto border-t border-slate-700 pt-4 md:border-none md:pt-0">
      <label class="text-sm font-medium text-slate-300 whitespace-nowrap">Szűrés csapattagra:</label>
      <select 
        v-model="model" 
        class="w-full md:w-64 bg-slate-900 border border-slate-600 text-white rounded-md p-2 text-sm focus:ring-2 focus:ring-red-500 focus:border-red-500 outline-none"
      >
        <option value="" class="bg-slate-900 text-white">-- Minden tag mutatása --</option>
        <option v-for="m in teamMembers" :key="m.id" :value="m.id" class="bg-slate-900 text-white">
          {{ m.name }}
        </option>
      </select>
    </div>

  </header>
</template>
