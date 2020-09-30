import axios from 'axios';
import { ICrudDeleteAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { FAILURE, REQUEST, SUCCESS } from 'app/shared/reducers/action-type.util';

import { defaultValue, INoteDeFrais } from 'app/shared/model/note-de-frais.model';

export const ACTION_TYPES = {
  FETCH_NOTEDEFRAIS_LIST: 'noteDeFrais/FETCH_NOTEDEFRAIS_LIST',
  FETCH_NOTEDEFRAIS: 'noteDeFrais/FETCH_NOTEDEFRAIS',
  CREATE_NOTEDEFRAIS: 'noteDeFrais/CREATE_NOTEDEFRAIS',
  UPDATE_NOTEDEFRAIS: 'noteDeFrais/UPDATE_NOTEDEFRAIS',
  DELETE_NOTEDEFRAIS: 'noteDeFrais/DELETE_NOTEDEFRAIS',
  RESET: 'noteDeFrais/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INoteDeFrais>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NoteDeFraisState = Readonly<typeof initialState>;

// Reducer

export default (state: NoteDeFraisState = initialState, action): NoteDeFraisState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NOTEDEFRAIS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NOTEDEFRAIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NOTEDEFRAIS):
    case REQUEST(ACTION_TYPES.UPDATE_NOTEDEFRAIS):
    case REQUEST(ACTION_TYPES.DELETE_NOTEDEFRAIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NOTEDEFRAIS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NOTEDEFRAIS):
    case FAILURE(ACTION_TYPES.CREATE_NOTEDEFRAIS):
    case FAILURE(ACTION_TYPES.UPDATE_NOTEDEFRAIS):
    case FAILURE(ACTION_TYPES.DELETE_NOTEDEFRAIS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOTEDEFRAIS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOTEDEFRAIS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NOTEDEFRAIS):
    case SUCCESS(ACTION_TYPES.UPDATE_NOTEDEFRAIS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NOTEDEFRAIS):
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

const apiUrl = 'api/note-de-frais';

// Actions

export const getEntities: ICrudGetAllAction<INoteDeFrais> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NOTEDEFRAIS_LIST,
  payload: axios.get<INoteDeFrais>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INoteDeFrais> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NOTEDEFRAIS,
    payload: axios.get<INoteDeFrais>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INoteDeFrais> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NOTEDEFRAIS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INoteDeFrais> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NOTEDEFRAIS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INoteDeFrais> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NOTEDEFRAIS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
