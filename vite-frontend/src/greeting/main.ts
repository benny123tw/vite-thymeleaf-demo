import '@/greeting/style.scss'
import { currentTime } from '@/utils/current-time.ts'
import { updateText } from '@/utils/update-text.ts'

document.addEventListener('DOMContentLoaded', () => {
  const greetingMessage = document.querySelector<HTMLDivElement>('h1')!
  const names = greetingMessage.textContent?.trim().split(',').slice(1) || []

  /**
   * Modify the greeting message generated by Thymeleaf.
   */
  greetingMessage.innerHTML = `
    Greeting! ${names.join(', ')}
  `

  const currentTimeElement = document.querySelector('#time')!
  currentTimeElement.innerHTML = `
    Current Time: ${currentTime()}
  `
  updateText()
})

