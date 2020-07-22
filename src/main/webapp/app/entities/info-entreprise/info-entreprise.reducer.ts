import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInfoEntreprise, defaultValue } from 'app/shared/model/info-entreprise.model';

export const ACTION_TYPES = {
  FETCH_INFOENTREPRISE_LIST: 'infoEntreprise/FETCH_INFOENTREPRISE_LIST',
  FETCH_INFOENTREPRISE: 'infoEntreprise/FETCH_INFOENTREPRISE',
  CREATE_INFOENTREPRISE: 'infoEntreprise/CREATE_INFOENTREPRISE',
  UPDATE_INFOENTREPRISE: 'infoEntreprise/UPDATE_INFOENTREPRISE',
  DELETE_INFOENTREPRISE: 'infoEntreprise/DELETE_INFOENTREPRISE',
  RESET: 'infoEntreprise/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInfoEntreprise>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InfoEntrepriseState = Readonly<typeof initialState>;

// Reducer

export default (state: InfoEntrepriseState = initialState, action): InfoEntrepriseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_INFOENTREPRISE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INFOENTREPRISE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INFOENTREPRISE):
    case REQUEST(ACTION_TYPES.UPDATE_INFOENTREPRISE):
    case REQUEST(ACTION_TYPES.DELETE_INFOENTREPRISE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_INFOENTREPRISE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INFOENTREPRISE):
    case FAILURE(ACTION_TYPES.CREATE_INFOENTREPRISE):
    case FAILURE(ACTION_TYPES.UPDATE_INFOENTREPRISE):
    case FAILURE(ACTION_TYPES.DELETE_INFOENTREPRISE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_INFOENTREPRISE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INFOENTREPRISE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INFOENTREPRISE):
    case SUCCESS(ACTION_TYPES.UPDATE_INFOENTREPRISE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INFOENTREPRISE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/info-entreprises';

// Actions

export const getEntities: ICrudGetAllAction<IInfoEntreprise> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INFOENTREPRISE_LIST,
  payload: axios.get<IInfoEntreprise>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInfoEntreprise> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INFOENTREPRISE,
    payload: axios.get<IInfoEntreprise>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInfoEntreprise> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INFOENTREPRISE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInfoEntreprise> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INFOENTREPRISE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInfoEntreprise> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INFOENTREPRISE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
