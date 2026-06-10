import { ref, onMounted, computed } from 'vue'
import axios from 'axios'

const API_BASE = 'http://localhost:8080/api'

export function useLeaveManagement() {
  const teamMembers = ref([])
  const leaveRequests = ref([])
  const onCallSchedule = ref([])
  const selectedMemberId = ref('')
  const errorMessage = ref('')
  const successMessage = ref('')

  const form = ref({
    teamMemberId: '',
    startDate: '',
    endDate: '',
    reason: ''
  })

  const filteredLeaveRequests = computed(() => {
    if (!selectedMemberId.value) return leaveRequests.value
    return leaveRequests.value.filter(
      req => req.teamMember.id === parseInt(selectedMemberId.value)
    )
  })

  const filteredOnCallSchedule = computed(() => {
    if (!selectedMemberId.value) return onCallSchedule.value
    return onCallSchedule.value.filter(
      week => week.onCallMember.id === parseInt(selectedMemberId.value)
    )
  })

  const loadData = async () => {
    try {
      const [membersRes, leavesRes, onCallRes] = await Promise.all([
        axios.get(`${API_BASE}/team-members`),
        axios.get(`${API_BASE}/leaves`),
        axios.get(`${API_BASE}/on-call?weeks=8`)
      ])
      teamMembers.value = membersRes.data
      leaveRequests.value = leavesRes.data
      onCallSchedule.value = onCallRes.data
    } catch (err) {
      errorMessage.value = 'Nem sikerült kommunikálni a Spring Boot backenddel.'
    }
  }

  const submitLeaveRequest = async () => {
    errorMessage.value = ''
    successMessage.value = ''
    
    if (!form.value.teamMemberId || !form.value.startDate || !form.value.endDate || !form.value.reason) {
      errorMessage.value = 'Kérlek, tölts ki minden mezőt!'
      return
    }

    try {
      await axios.post(`${API_BASE}/leaves`, {
        requestId: null,
        teamMemberId: parseInt(form.value.teamMemberId),
        startDate: form.value.startDate,
        endDate: form.value.endDate,
        reason: form.value.reason
      })
      
      successMessage.value = 'Szabadságigény sikeresen rögzítve (Pending)!'
      form.value.startDate = ''
      form.value.endDate = ''
      form.value.reason = ''
      await loadData()
    } catch (err) {
      errorMessage.value = err.response?.data?.message || 'Hiba történt a mentés során.'
    }
  }

  const updateLeaveStatus = async (id, status) => {
    errorMessage.value = ''
    successMessage.value = ''
    try {
      await axios.put(`${API_BASE}/leaves/${id}/status?status=${status}`)
      await loadData()
    } catch (err) {
      errorMessage.value = err.response?.data?.message || 'Nem sikerült frissíteni a státuszt.'
    }
  }

  onMounted(() => {
    loadData()
  })

  return {
    teamMembers,
    leaveRequests,
    onCallSchedule,
    filteredLeaveRequests,
    filteredOnCallSchedule,
    selectedMemberId,
    errorMessage,
    successMessage,
    form,
    submitLeaveRequest,
    updateLeaveStatus
  }
}
