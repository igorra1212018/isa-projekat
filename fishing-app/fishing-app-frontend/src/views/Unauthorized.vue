<template>
    <div class="unauthorized-panel">
        <h2>403 Unauthorized</h2>
        <p>You do not have permission to access this page.</p>
    </div>
</template>

<script>

export default {
    name: 'Unauthorized',
    data(){
        return {

        }
    },
    methods: {

    }
}

</script>

<style>
    .unauthorized-panel {
        background-color: rgba(255,255, 255, 1);
        position:absolute;
        top:25%;
        width:400px;
        right:calc(50% - 200px);
        padding: 40px;
        transition:.3s ease-in-out;
        z-index:0;
        box-shadow: 0 0 15px 9px #00000096;
    }
</style>