<template>
  <div class="home-page">
    <img
        alt="page logo"
        src="@/assets/briis-scrabble.png"
        class="logo"
    >
    <div class="form-container">
      <div>
        <input
            type="text"
            v-model="word"
            placeholder="Enter a word"
            @input="validateInput"
        />
        <button @click="submitWord" :disabled="errorMessage">Submit</button>

      </div>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      <p v-if="successMessage" class="success">{{ successMessage }}</p>
    </div>

    <div v-if="response" class="response">
      <div v-if="response.inLibrary">
        <p><strong>{{ response.word }}</strong> is in our dictionary!</p>
        <p>Score: <strong>{{ response.score }}</strong></p>
      </div>
      <div v-else>
        <p><strong>{{ response.word }}</strong> is not yet in our dictionary. Would you like to add it?</p>
        <button @click="saveWord">Add</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      word: "",
      response: null,
      errorMessage: "",
      successMessage: "",
    };
  },
  methods: {
    validateInput() {
      this.response = null;
      if (!this.word.trim()) {
        this.errorMessage = "Please enter a word!";
      } else if (!this.word.match("^[A-ZÕÄÖÜa-zõäöü]+$")) {
        this.errorMessage = "Word may not contain numbers or special characters.";
      } else {
        this.errorMessage = null;
      }
    },

    async submitWord() {
      if (this.errorMessage) {
        return;
      }
      try {
        const res = await axios.get(`/api/words/check/${this.word}`);
        this.response = res.data;
      } catch (error) {
        console.error("Error checking word:", error);
        this.errorMessage = error.response.data.message;
      }
    },

    async saveWord() {
      if (this.errorMessage) {
        return;
      }
      try {
        const res = await axios.post('/api/words/add', this.word, {
          headers: {'Content-Type': 'text/plain'}
        });
        this.successMessage = res.data.message;
        this.word = "";
        this.response = null;
      } catch (error) {
        console.error("Error adding word:", error);
        this.errorMessage = error.response?.data?.message || "An error occurred while adding the word.";
      }
    }
  },
};
</script>

<style scoped>

.home-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  font-family: Helvetica, Arial, sans-serif;
  font-weight: 500;
}

p {
  font-size: 14px;
}

.logo {
  width: 80%;
  max-width: 600px;
  height: auto;
}

.form-container {
  margin: 20px auto;
}

input {
  background-color: #cccccc;
  padding: 10px;
  font-size: 14px;
  margin-right: 10px;
  border-radius: 8px;
  border-style: none;
  outline: none;
}

input:hover,
input:focus {
  background-color: #dedede;
}

button {
  background-color: #38b6ff;
  color: white;
  padding: 10px 20px;
  font-size: 14px;
  cursor: pointer;
  border-radius: 8px;
  border-style: none;
  outline: none;
}

button:hover,
button:focus {
  background-color: #76c9ff;
}

button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.response {
  margin-top: 20px;
  font-size: 18px;
}

.error {
  color: red;
  font-size: 16px;
  margin-top: 5px;
}

.success {
  color: #38b6ff;
  font-size: 16px;
  margin-top: 5px;
}
</style>
