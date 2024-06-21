export function updateText() {
  const text = 'You could modify the text in the updateText function to see how HMR works.'
  const elem = document.querySelector('#update-text')!

  if (elem) {
    elem.textContent = elem.textContent?.replace(/\(.*\)/, `(${ text })`) || elem.textContent
  }
}

if (import.meta.hot) {
  import.meta.hot.accept((m) => {
    console.log(m)
    if (!m) return
    // change `text` will trigger the HMR
    // and time will not be updated
    m.updateText()
  })
}
