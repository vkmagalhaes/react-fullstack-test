FROM viniciusmkm/nodejs:4.4.0-onbuild

CMD ["dumb-init", "gosu", "app", "npm", "start"]

EXPOSE 3000
