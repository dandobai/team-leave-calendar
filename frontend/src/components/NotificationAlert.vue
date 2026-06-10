<script setup>
defineProps({
  message: {
    type: String,
    required: true
  },
  type: {
    type: String,
    default: 'error',
    validator: (value) => ['error', 'success'].includes(value)
  }
})
</script>

<template>
  <div 
    v-if="message" 
    :class="type === 'success' 
      ? 'bg-green-100 border-green-400 text-green-700' 
      : 'bg-red-100 border-red-400 text-red-700'"
    class="border px-4 py-3 rounded shadow transition-all duration-300"
  >
    {{ message }}
  </div>
</template>
