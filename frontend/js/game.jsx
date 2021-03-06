let React = require('react'),

    DataLoadErrorRefresh = require('./errors.jsx').dataLoadErrorRefresh;

class Choice extends React.Component {
  choose() {
    this.props.onChoose();
  }

  render() {
    return (
      <div className={'choice ui basic button' +
                      (this.props.isDisabled ? ' disabled' : '') +
                      (this.props.isCorrect ? ' green' :
                          this.props.isIncorrect ? ' red' :
                              this.props.isChoice ? ' grey' : '')}
           onClick={this.props.isDisabled ? null : this.choose.bind(this)}>
        <span>{this.props.value}</span>
      </div>
    );
  }
}

class Game extends React.Component {
  constructor(props, context) {
    super(props, context);

    let self = this;

    this._showingResultTimeout = null;

    this.choose = [];
    for (let i = 0; i < 4; i++) {
      (function(j) {
        self.choose.push(() => self.props.onChoose(j));
      })(i);
    }
  }

  componentDidUpdate() {
    let self = this;

    if (this.props.isShowingResult) {
      this._showingResultTimeout !== null && clearTimeout(this._showingResultTimeout);
      this._showingResultTimeout = setTimeout(() => {
        clearTimeout(self._showingResultTimeout);
        self._showingResultTimeout = null;

        self.props.onNextWord();
      }, 1500);
    }
  }
  componentWillUnmount() {
    this._showingResultTimeout !== null && clearTimeout(this._showingResultTimeout);
    this._showingResultTimeout = null;
  }

  render() {
    let choices = [];

    if (this.props.word && !this.props.word.error)
    for (let i = 0; i < 4; i++) {
      let choice =
        <Choice {...this.props.word.choices[i]} key={i}
                isDisabled={this.props.isDisabled}
                isChoice={i === this.props.choice}
                isCorrect={i === this.props.correctChoice}
                isIncorrect={this.props.isShowingResult && this.props.choice === i && this.props.choice !== this.props.correctChoice}
                onChoose={this.choose[i]} />;
      choices.push(choice);
    }

    return (
      <div className={'game' + (this.props.isVerbs ? ' verbs' : '')}>
        {this.props.isLoading ? <span>Загрузка...</span> :
        
         this.props.isError ? <DataLoadErrorRefresh refresh={this.props.refresh} /> :
        
         !this.props.word.error ? [
           <div key='word' className='word-box ui message'><span>{this.props.word.word}</span></div>,
           <div key='choices' className='choices'>
             <div className='row'>{choices[0]}{choices[1]}</div>
             <div className='row'>{choices[2]}{choices[3]}</div>
           </div>
         ] : (
           <div className='ui message error'>
             <span>{this.props.word.error === 'no-words' ? 'Добавьте больше слов' : this.props.word.error}</span>
           </div>
         )}
      </div>
    );
  }
}

exports.Choice = Choice;
exports.Game = Game;
