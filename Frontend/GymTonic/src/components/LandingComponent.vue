<template>
  <main class="main">
    <slot></slot>

    <div class="main__ball one"></div>
    <div class="main__ball two"></div>
  </main>
</template>

<style lang="scss">
.main {
  overflow: hidden;
  position: relative;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  flex-direction: column;

  &__ball {
    position: absolute;
    background-color: var(--primary);
    height: 20rem;
    width: 20rem;
    border-radius: 10em;
    filter: blur(5rem);
      z-index: -1;

    &.one {
      top: -10rem;
      left: -10rem;
    }

    &.two {
      bottom: -10rem;
      right: -10rem;
    }
  }
}
</style>
