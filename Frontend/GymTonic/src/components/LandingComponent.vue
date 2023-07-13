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
  color: white;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  flex-direction: column;

  &__ball {
    position: absolute;
    background-color: var(--orange-color);
    height: 20rem;
    width: 20rem;
    border-radius: 10em;
    filter: blur(5rem);

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
