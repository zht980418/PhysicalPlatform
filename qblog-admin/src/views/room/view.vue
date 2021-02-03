<template>
  <div class="app-main-container">
    <el-row>
      <el-col
        :span="7"
        :offset="9"
      >
        <h1
          @mouseover="alertVisible=true"
          @mouseleave="alertVisible=false"
        >{{ room.name }}使用情况</h1>
      </el-col>
      <el-col :span="5">
        <el-alert
          v-show="alertVisible&(!roomConfig.selectable)"
          type="warning"
          title="管理"
          description="查看时间表时无法编辑"
          show-icon
          :closable="false"
        />
        <el-alert
          v-show="alertVisible&(roomConfig.selectable)"
          type="warning"
          title="管理"
          description="点击时间表可直接添加/修改预约"
          show-icon
          :closable="false"
        />
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :span="13"
        :offset="5"
      >
        <el-form
          :model="room"
          :inline="true"
          :disabled="!roomConfig.selectable"
        >
          <el-form-item
            label="咨询室名："
            :label-width="formLabelWidth"
          >
            <el-input v-model="room.name" />
          </el-form-item>
          <el-form-item
            label="咨询室地址："
            :label-width="formLabelWidth"
          >
            <el-input v-model="room.address" />
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="4">
        <el-button
          v-if="roomConfig.selectable"
          type="success"
          icon="el-icon-check"
          plain
          @click="handleInfoUpdate"
        >确认修改</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :span="20"
        :offset="2"
      >
        <el-card>
          <FullCalendar
            class="demo-app-calendar"
            :options="roomConfig"
          >
            <template v-slot:eventContent="arg">
              <b>{{ arg.timeText }}</b>
              <i>{{ arg.event.title }}</i>
            </template>
          </FullCalendar>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { INITIAL_EVENTS, createEventId, defaultConstraint } from '@/utils/event-utils'
import '@fullcalendar/core/locales/zh-cn'
// import { getConstraint, postOrder } from '@/api/order'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      alertVisible: false,
      formLabelWidth: '120px',
      room: {
        id: 'newId',
        name: 'newRoom',
        address: 'newAddress',
      },
      roomConfig: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: '',
          center: 'title',
          right: ''
        },
        businessHours: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '09:00',
            endTime: '12:00'
          },
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '14:00',
            endTime: '18:00'
          },
        ],
        initialView: 'timeGridWeek',
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true, // 拖动并选择多个时段
        selectConstraint: [ // specify an array instead
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '09:00',
            endTime: '12:00'
          },
          {
            daysOfWeek: [1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday
            startTime: '14:00', // 8am
            endTime: '18:00' // 6pm
          }
        ],
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        allDaySlot: false,
        slotMinTime: '09:00:00',
        slotMaxTime: '18:00:00',
        slotDuration: '01:00:00',
        expandRows: true,
        locale: 'zh-cn',
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents,
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
    }
  },
  created() {
    // 获取room信息
    this.room.id = this.$route.params.id
    this.room.name = this.$route.params.name
    this.room.address = this.$route.params.address
    // 获取查看/编辑状态
    this.roomConfig.selectable = Boolean(this.$route.params.op)
    // 获取限制信息
    this.roomConfig.businessHours = defaultConstraint()
    this.roomConfig.selectConstraint = defaultConstraint()
    // TODO  获取日程表数据
  },
  methods: {
    // 基本信息
    handleInfoUpdate() {
      // TODO 修改基本信息
    },

    // 预约
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },

    handleDateSelect(selectInfo) {
      const calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (selectInfo.startStr) {
        calendarApi.addEvent({
          id: createEventId(),
          title: 'this.form.name',
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }
    },

    // 点击已有预约
    handleEventClick(clickInfo) {
      // TODO 跳转到添加修改预约？
    },

    handleEvents(events) {
      this.currentEvents = events
    },

    errorHandler() {
      return true
    },
  }

}
</script>