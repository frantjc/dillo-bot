import axios from 'axios';
import http from 'http';

class ResponseStatusError extends Error {}

const api = axios.create({
    baseURL: '/api',
    headers: {},
    timeout: 5000,
    httpAgent: new http.Agent({ keepAlive: true }),
});

type DiscordUser = {
    id: string,
    name: string,
    discriminator: string,
    birthday?: Date,
}

function getDiscordUsers(): Promise<DiscordUser[]> {
    return api.get('/users/discord').then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function getDiscordUser(id: string): Promise<DiscordUser | any> {
    return api.get(`/users/discord/${id}`).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

type UserDetails = {
    birthday: string,
}

function getDiscordUserDetails(id: string): Promise<UserDetails | any> {
    return api.get(`/users/discord/${id}/details`).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function getDiscordUserBirthday(id: string): Promise<string | any> {
    return api.get(`/users/discord/${id}/birthday`).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function addDiscordUserBirthday(id: string, birthday: string): Promise<DiscordUser> {
    return api.post(`/users/discord/${id}/birthday`, birthday).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
  });
}

function linkDiscordUserToGitHub(discord_id: string, github_id: number) {
    api.post(`/users/discord/${discord_id}/link`, github_id).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

type GitHubUser = {
    login: string,
    id: number,
    node_id: string,
    url: URL,
    html_url: URL,
    followers_url: URL,
    following_url: URL,
    gists_url: URL,
    starred_url: URL,
    subscriptions_url: URL,
    organizations_url: URL,
    repos_url: URL,
    events_url: URL,
    received_events_url: URL,
    type: string,
    site_admin: boolean,
}

function getGitHubUsers(): Promise<GitHubUser[]> {
    return api.get('/users/github').then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function getGitHubUser(id: number): Promise<GitHubUser | any> {
    return api.get(`/users/github/${id}`).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function linkGitHubUserToDiscord(github_id: number, discord_id: string): Promise<GitHubUser> {
    return api.post(`/users/github/${github_id}`, discord_id).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

type DiscordChannel = {
    id: string,
    name: string,
    subscriptions?: string[],
}

function getDiscordChannels(): Promise<DiscordChannel[]> {    
    return api.get('/channels').then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function getDiscordChannel(id: string): Promise<DiscordChannel | any> {
    return api.get(`/channels/${id}`).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function getDiscordChannelSubscriptions(id: string): Promise<string[] | any> {
    return api.get(`/channels/${id}/subscriptions`).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

function addDiscordChannelSubscription(id: string, subscription: string): Promise<DiscordChannel | any> {
    return api.post(`/channels/${id}/subscriptions`, subscription).then(response => {
      if (200 <= response.status && response.status < 300) {
        return response.data;
      } throw new ResponseStatusError(`${response.status}`);
    }).catch(error => {
      console.log(error);
      return Promise.reject(error);
    });
}

export default api;

export type {
    DiscordUser,
    GitHubUser,
    DiscordChannel,
    UserDetails,
}

export {
    getDiscordUsers,
    getDiscordUser,
    getDiscordUserDetails,
    getDiscordUserBirthday,
    addDiscordUserBirthday,
    linkDiscordUserToGitHub,
    getGitHubUsers,
    getGitHubUser,
    linkGitHubUserToDiscord,
    getDiscordChannels,
    getDiscordChannel,
    getDiscordChannelSubscriptions,
    addDiscordChannelSubscription,
}