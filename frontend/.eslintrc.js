module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/typescript/recommended'
  ],
  parserOptions: {
    ecmaVersion: 2020
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    "@typescript-eslint/no-this-alias": [
      "error",
      {
        "allowDestructuring": false, // Disallow `const { props, state } = this`; true by default
        "allowedNames": ["that"] // Allow `const self = this`; `[]` by default
      },
    ]
  }

}
