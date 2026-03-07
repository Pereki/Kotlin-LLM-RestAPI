<script setup lang="ts">
import { ref } from 'vue'

const apiResponse = ref<any>(null)
const messages = ref<string[]>([])
const loading = ref(false)
const error = ref<string | null>(null)
const input = ref('')

async function sendPrompt() {
  loading.value = true
  error.value = null

  if(apiResponse.value){
    messages.value.push(apiResponse.value.message ?? String(apiResponse.value))
  }

  apiResponse.value = null
  try {
    const res = await fetch('http://localhost:8082/prompt', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message: input.value, context: messages.value }),
    })
    if (!res.ok) throw new Error(`${res.status} ${res.statusText}`)
     
    apiResponse.value = await res.json()
    messages.value = apiResponse.value.context ?? []
  } catch (e: any) {
    error.value = e?.message ?? String(e)
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <h1>Garmin - Interface</h1>
  <div>
    <input class="input" v-model="input" placeholder="Enter your prompt" />
    <div>
      <button type="button" @click="sendPrompt" :disabled="loading">Send prompt</button>
      <p v-if="loading">Sending prompt…</p>
      <p v-if="error" class="error">Error: {{ error }}</p>
      <div v-if="apiResponse" class="api-response">{{ (apiResponse && apiResponse.message) ? apiResponse.message : apiResponse }}</div>
      
      <div v-if="messages.length" class="messages">
        <h2>Messages</h2>
          <div v-for="(message, index) in messages.reverse()" :key="index">{{index % 2 == 0 ? 'User:' : 'Bot:'}} {{ message }}</div>
      </div>
    </div>
</div>
</template>

<style scoped>
.input {
  padding: 0.5em;
  margin-right: 0.5em;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-bottom: 3rem;
  width: 50rem;
}

.api-response {
  background: #f8fafc;
  padding: 12px;
  border-radius: 8px;
  color: #0f172a;
  white-space: pre-wrap;
  box-shadow: 0 4px 12px rgba(2,6,23,0.04);
}
</style>
