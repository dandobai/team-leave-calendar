<script setup>
defineProps({
  teamMembers: {
    type: Array,
    required: true
  }
})

const form = defineModel({ type: Object, required: true })
defineEmits(['submit'])
</script>

<template>
  <div class="bg-slate-800 border border-slate-700 p-6 rounded-lg shadow-md h-fit text-white">
    <h2 class="text-lg font-semibold text-white mb-4 uppercase tracking-wide">Új szabadság igénylése</h2>
    
    <form @submit.prevent="$emit('submit')" class="space-y-4">
      
      <div>
        <label class="block text-sm font-medium text-slate-300 mb-1">Csapattag</label>
        <select 
          v-model="form.teamMemberId" 
          class="w-full bg-slate-900 border border-slate-600 text-white rounded p-2 text-sm focus:ring-2 focus:ring-red-500 focus:border-red-500 outline-none"
        >
          <option value="" class="bg-slate-900 text-white">Válassz valakit...</option>
          <option 
            v-for="m in teamMembers" 
            :key="m.id" 
            :value="m.id" 
            class="bg-slate-900 text-white"
          >
            {{ m.name }}
          </option>
        </select>
      </div>

      <div class="grid grid-cols-2 gap-4">
        <div>
          <label class="block text-sm font-medium text-slate-300 mb-1">Kezdőlap</label>
          <input 
            type="date" 
            v-model="form.startDate" 
            class="w-full bg-slate-900 border border-slate-600 text-white rounded p-2 text-sm color-scheme-dark focus:ring-2 focus:ring-red-500 focus:border-red-500 outline-none" 
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-slate-300 mb-1">Végdátum</label>
          <input 
            type="date" 
            v-model="form.endDate" 
            class="w-full bg-slate-900 border border-slate-600 text-white rounded p-2 text-sm color-scheme-dark focus:ring-2 focus:ring-red-500 focus:border-red-500 outline-none" 
          />
        </div>
      </div>

      <div>
        <label class="block text-sm font-medium text-slate-300 mb-1">Indoklás</label>
        <textarea 
          v-model="form.reason" 
          rows="3" 
          class="w-full bg-slate-900 border border-slate-600 text-white rounded p-2 text-sm placeholder-slate-500 focus:ring-2 focus:ring-red-500 focus:border-red-500 outline-none" 
          placeholder="Miért mész szabadságra?"
        ></textarea>
      </div>

      <button 
        type="submit" 
        class="w-full bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded text-sm uppercase tracking-wider transition-colors duration-200 shadow-md"
      >
        Igénylés beküldése
      </button>

    </form>
  </div>
</template>

<style scoped>
.color-scheme-dark {
  color-scheme: dark;
}
</style>
